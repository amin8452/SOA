import './LoadingState.css';

function LoadingState() {
  return (
    <div className="loading">
      <div className="loading__spinner" />
      <p>Connexion au service... </p>
    </div>
  );
}

export default LoadingState;
