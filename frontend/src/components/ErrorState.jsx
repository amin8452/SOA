import './ErrorState.css';

function buildMessage(error) {
  if (!error) {
    return "Une erreur inconnue s'est produite.";
  }
  if (error.code === 'ERR_NETWORK' || error.message === 'Network Error') {
    return "Impossible de joindre le backend. Verifiez que le service Spring Boot tourne.";
  }
  if (error.response?.data?.message) {
    return error.response.data.message;
  }
  if (error.message) {
    return error.message;
  }
  return String(error);
}

function ErrorState({ error, onRetry }) {
  return (
    <div className="error">
      <h2>Impossible de charger les restaurants</h2>
      <p>{buildMessage(error)}</p>
      <button type="button" onClick={onRetry}>
        R&eacute;essayer
      </button>
    </div>
  );
}

export default ErrorState;
