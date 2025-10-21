import { useEffect, useState } from 'react';
import './OrderModal.css';

function OrderModal({ restaurant, dish, open, onClose, onConfirm, loading }) {
  const [quantity, setQuantity] = useState(1);
  const [expectedPrice, setExpectedPrice] = useState(() => Number(dish.price ?? dish.prix ?? 0));

  useEffect(() => {
    setQuantity(1);
    setExpectedPrice(Number(dish.price ?? dish.prix ?? 0));
  }, [dish]);

  if (!open) {
    return null;
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    onConfirm({
      quantity,
      expectedUnitPrice: Number(expectedPrice)
    });
  };

  const handleOverlayClick = (event) => {
    if (event.target === event.currentTarget) {
      onClose();
    }
  };

  return (
    <div className="modal__overlay" onClick={handleOverlayClick} role="presentation">
      <div className="modal">
        <button type="button" className="modal__close" onClick={onClose} aria-label="Fermer">
          &times;
        </button>
        <header className="modal__header">
          <span className="modal__context">{restaurant.name}</span>
          <h2>Commander {dish.name ?? dish.nom}</h2>
          <p>
            Confirmez les d&eacute;tails avant envoi. Le montant total sera calcul&eacute; par le backend.
          </p>
        </header>
        <form className="modal__form" onSubmit={handleSubmit}>
          <label className="modal__field">
            <span>Quantit&eacute;</span>
            <div className="modal__counter">
              <button
                type="button"
                onClick={() => setQuantity((q) => Math.max(1, q - 1))}
                aria-label="Diminuer la quantite"
                disabled={quantity <= 1 || loading}
              >
                -
              </button>
              <input
                type="number"
                min="1"
                value={quantity}
                onChange={(event) =>
                  setQuantity(Math.max(1, Number.parseInt(event.target.value, 10) || 1))
                }
                disabled={loading}
              />
              <button
                type="button"
                onClick={() => setQuantity((q) => q + 1)}
                aria-label="Augmenter la quantite"
                disabled={loading}
              >
                +
              </button>
            </div>
          </label>
          <label className="modal__field">
            <span>Prix attendu (MAD)</span>
            <input
              type="number"
              min="0.01"
              step="0.01"
              value={expectedPrice}
              onChange={(event) => {
                const parsed = Number(event.target.value);
                setExpectedPrice(Number.isNaN(parsed) ? 0 : parsed);
              }}
              disabled={loading}
            />
            <small>
              Doit correspondre au prix affich&eacute; (
              {Number(dish.price ?? dish.prix ?? 0).toFixed(2)} MAD)
            </small>
          </label>
          <button type="submit" className="modal__submit" disabled={loading}>
            {loading ? 'Envoi en cours...' : 'Valider la commande'}
          </button>
        </form>
      </div>
    </div>
  );
}

export default OrderModal;
