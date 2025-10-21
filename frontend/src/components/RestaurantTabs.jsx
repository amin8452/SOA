import './RestaurantTabs.css';

function RestaurantTabs({ restaurants, selectedRestaurantId, onSelect }) {
  return (
    <div className="tabs">
      {restaurants.map((restaurant) => {
        const isActive = restaurant.id === selectedRestaurantId;
        return (
          <button
            key={restaurant.id}
            type="button"
            className={`tabs__item ${isActive ? 'tabs__item--active' : ''}`}
            onClick={() => onSelect(restaurant.id)}
          >
            <span className="tabs__name">{restaurant.name}</span>
            <span className="tabs__count">{restaurant.plats?.length ?? 0} plats</span>
          </button>
        );
      })}
    </div>
  );
}

export default RestaurantTabs;
