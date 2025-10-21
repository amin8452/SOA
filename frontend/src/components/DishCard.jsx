import './DishCard.css';

function DishCard({ dish, restaurant, onOrder }) {
  const isAvailable = dish.available ?? dish.disponible ?? true;
  const rawPrice = dish.price ?? dish.prix ?? 0;
  const price = Number(rawPrice) || 0;
  const name = dish.name ?? dish.nom ?? 'Plat';

  return (
    <article className={`dish-card ${isAvailable ? '' : 'dish-card--disabled'}`}>
      <div className="dish-card__header">
        <h3>{name}</h3>
        <span className="dish-card__price">{price.toFixed(2)} MAD</span>
      </div>
      <p className="dish-card__description">
        Une s&eacute;lection de {restaurant.name}. Prix garantis par le service.
      </p>
      <footer className="dish-card__footer">
        <span className={`dish-card__status ${isAvailable ? 'is-available' : 'is-unavailable'}`}>
          {isAvailable ? 'Disponible' : 'Indisponible'}
        </span>
        <button
          type="button"
          className="dish-card__order"
          disabled={!isAvailable}
          onClick={() => onOrder(restaurant, dish)}
        >
          Commander
        </button>
      </footer>
    </article>
  );
}

export default DishCard;
