import React from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import App from './App'
import Clientes from './pages/Clientes'
import Reservas from './pages/Reservas'
import './index.css'

createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
      <Routes>
        <Route path="/" element={<Clientes/>} />
        <Route path="/reservas" element={<Reservas/>} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
)
