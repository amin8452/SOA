import { useEffect } from 'react';
import './FeedbackToast.css';

const AUTO_DISMISS_DELAY = 5400;

function FeedbackToast({ feedback, onDismiss }) {
  useEffect(() => {
    if (!feedback) {
      return;
    }
    const timer = setTimeout(() => {
      onDismiss();
    }, AUTO_DISMISS_DELAY);
    return () => {
      clearTimeout(timer);
    };
  }, [feedback, onDismiss]);

  if (!feedback) {
    return null;
  }

  const variant = feedback.type === 'success' ? 'toast--success' : 'toast--error';

  return (
    <div className={`toast ${variant}`}>
      <div className="toast__content">
        <strong>{feedback.message}</strong>
        {feedback.details && <span className="toast__details">{feedback.details}</span>}
      </div>
      <button type="button" className="toast__close" onClick={onDismiss} aria-label="Fermer">
        &times;
      </button>
    </div>
  );
}

export default FeedbackToast;
