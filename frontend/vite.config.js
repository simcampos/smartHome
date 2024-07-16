// vite.config.js
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  optimizeDeps: {
    include: ['@emotion/react', '@emotion/styled'],
  },
  plugins: [react()],
});
