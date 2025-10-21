import DishCard from './DishCard.jsx';
import './DishGallery.css';

function DishGallery({ restaurant, onOrder }) {
  if (!restaurant) {
    return (
      <section className="gallery__empty">
        <h2>S&eacute;lectionnez un restaurant</h2>
        <p>Choisissez un &eacute;tablissement pour afficher ses plats disponibles.</p>
      </section>
    );
  }

  const dishes = restaurant.plats || [];

  return (
    <section className="gallery">
      <header className="gallery__header">
        <div>
          <h2>{restaurant.name}</h2>
          <p>
            Retrouvez les sp&eacute;cialit&eacute;s de ce restaurant et commandez en toute confiance.
          </p>
        </div>
        <div className="gallery__badge">
          {dishes.length} {dishes.length > 1 ? 'options' : 'option'}
        </div>
      </header>
      <div className="gallery__grid">
        {dishes.map((dish) => (
          <DishCard
            key={dish.id}
            restaurant={restaurant}
            dish={dish}
            onOrder={onOrder}
          />
        ))}
      </div>
    </section>
  );
}

export default DishGallery;
