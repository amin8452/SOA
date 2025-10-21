import { useEffect, useMemo, useState } from 'react';
import { fetchRestaurants, submitOrder } from './api/client.js';
import Header from './components/Header.jsx';
import RestaurantTabs from './components/RestaurantTabs.jsx';
import DishGallery from './components/DishGallery.jsx';
import OrderModal from './components/OrderModal.jsx';
import LoadingState from './components/LoadingState.jsx';
import ErrorState from './components/ErrorState.jsx';
import FeedbackToast from './components/FeedbackToast.jsx';
import './styles/App.css';

function App() {
  const [restaurants, setRestaurants] = useState([]);
  const [selectedRestaurantId, setSelectedRestaurantId] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [orderTarget, setOrderTarget] = useState(null);
  const [orderFeedback, setOrderFeedback] = useState(null);
  const [submittingOrder, setSubmittingOrder] = useState(false);

  useEffect(() => {
    let mounted = true;
    async function loadRestaurants() {
      setLoading(true);
      setError(null);
      try {
        const data = await fetchRestaurants();
        if (!mounted) {
          return;
        }
        setRestaurants(data);
        setSelectedRestaurantId((current) => {
          if (current !== null && data.some((resto) => resto.id === current)) {
            return current;
          }
          return data[0]?.id ?? null;
        });
      } catch (err) {
        if (!mounted) {
          return;
        }
        setError(err);
      } finally {
        if (mounted) {
          setLoading(false);
        }
      }
    }
    loadRestaurants();
    return () => {
      mounted = false;
    };
  }, []);

  const selectedRestaurant = useMemo(() => {
    return restaurants.find((resto) => resto.id === selectedRestaurantId) || null;
  }, [restaurants, selectedRestaurantId]);

  const handleOpenOrder = (restaurant, dish) => {
    setOrderTarget({ restaurant, dish });
  };

  const handleCloseOrder = () => {
    setOrderTarget(null);
  };

  const handleSubmitOrder = async ({ quantity, expectedUnitPrice }) => {
    if (!orderTarget) {
      return;
    }
    setSubmittingOrder(true);
    setOrderFeedback(null);
    try {
      const payload = {
        restaurantId: orderTarget.restaurant.id,
        platId: orderTarget.dish.id,
        quantity,
        expectedUnitPrice
      };
      const confirmation = await submitOrder(payload);
      const totalPrice = Number(confirmation.totalPrice ?? 0);
      setOrderFeedback({
        type: confirmation.accepted ? 'success' : 'error',
        message: confirmation.message,
        details: confirmation.accepted
          ? `Total: ${totalPrice.toFixed(2)} MAD`
          : null
      });
      if (confirmation.accepted) {
        handleCloseOrder();
      }
    } catch (err) {
      const responseMessage =
        err.response?.data?.message ||
        err.response?.data ||
        err.message ||
        "Impossible d'envoyer la commande.";
      setOrderFeedback({
        type: 'error',
        message: responseMessage
      });
    } finally {
      setSubmittingOrder(false);
    }
  };

  const handleRetry = () => {
    setError(null);
    setLoading(true);
    fetchRestaurants()
      .then((data) => {
        setRestaurants(data);
        setSelectedRestaurantId(data[0]?.id ?? null);
      })
      .catch((err) => setError(err))
      .finally(() => setLoading(false));
  };

  return (
    <div className="app-shell">
      <Header restaurantCount={restaurants.length} />
      <main className="app-content">
        {loading && <LoadingState />}
        {!loading && error && <ErrorState onRetry={handleRetry} error={error} />}
        {!loading && !error && restaurants.length === 0 && (
          <div className="empty-state">
            <h2>Aucun restaurant disponible</h2>
            <p>Les menus ne sont pas encore disponibles. Revenez plus tard.</p>
          </div>
        )}
        {!loading && !error && restaurants.length > 0 && (
          <>
            <RestaurantTabs
              restaurants={restaurants}
              selectedRestaurantId={selectedRestaurantId}
              onSelect={setSelectedRestaurantId}
            />
            <DishGallery restaurant={selectedRestaurant} onOrder={handleOpenOrder} />
          </>
        )}
      </main>
      {orderTarget && (
        <OrderModal
          restaurant={orderTarget.restaurant}
          dish={orderTarget.dish}
          open={Boolean(orderTarget)}
          onClose={handleCloseOrder}
          onConfirm={handleSubmitOrder}
          loading={submittingOrder}
        />
      )}
      <FeedbackToast feedback={orderFeedback} onDismiss={() => setOrderFeedback(null)} />
    </div>
  );
}

export default App;
