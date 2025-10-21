import axios from 'axios';

const NORMALIZED_ENV_URL = import.meta.env.VITE_API_BASE_URL
  ? import.meta.env.VITE_API_BASE_URL.replace(/\/$/, '')
  : null;

const RUNTIME_ORIGIN =
  typeof window !== 'undefined' ? window.location.origin.replace(/\/$/, '') : '';

const API_BASE_URL =
  NORMALIZED_ENV_URL ||
  (import.meta.env.DEV ? '/api/v1' : `${RUNTIME_ORIGIN || ''}/api/v1`) ||
  '/api/v1';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json'
  },
  timeout: 10000
});

export const fetchRestaurants = async () => {
  const { data } = await api.get('/restaurants');
  return data;
};

export const fetchMenu = async () => {
  const { data } = await api.get('/menus');
  return data;
};

export const submitOrder = async (order) => {
  const { data } = await api.post('/commandes', order);
  return data;
};

export default api;
