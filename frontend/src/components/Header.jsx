import './Header.css';

function Header({ restaurantCount }) {
  return (
    <header className="hero">
      <div className="hero__badge">SpeedLiv</div>
      <div className="hero__content">
        <h1>Vos menus en un clin d&apos;oeil</h1>
        <p>
          Explorez les restaurants disponibles et passez vos commandes en toute confiance.
          Les informations de disponibilit&eacute; et de prix proviennent directement du
          service Spring Boot.
        </p>
        <div className="hero__meta">
          <span className="hero__stat">
            <strong>{restaurantCount}</strong> restaurants connect&eacute;s
          </span>
        </div>
      </div>
    </header>
  );
}

export default Header;
