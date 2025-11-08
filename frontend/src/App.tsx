import React from 'react'
import { Link } from 'react-router-dom'

export default function App(){
  return (
    <div>
      <header style={{padding: '1rem', borderBottom: '1px solid #ddd'}}>
        <h1>SisReservas</h1>
        <nav>
          <Link to="/">Clientes</Link> | <Link to="/reservas">Reservas</Link>
        </nav>
      </header>
      <main style={{padding: '1rem'}}>
        {/* Route outlet will render pages */}
      </main>
    </div>
  )
}
