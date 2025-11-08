import React, { useEffect, useState } from 'react'
import axios from 'axios'

type Reserva = {
  id: number
  data: string
  hora: string
  observacoes?: string
  status: string
  cliente: { id:number, nome:string }
}

export default function Reservas(){
  const [reservas, setReservas] = useState<Reserva[]>([])
  const [loading, setLoading] = useState(true)

  useEffect(()=>{
    axios.get('/api/reservas')
      .then(res=> setReservas(res.data))
      .catch(err=> console.error(err))
      .finally(()=> setLoading(false))
  },[])

  if(loading) return <div>Carregando reservas...</div>

  return (
    <div>
      <h2>Reservas</h2>
      <table style={{width: '100%', borderCollapse: 'collapse'}}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Data</th>
            <th>Hora</th>
            <th>Cliente</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {reservas.map(r=> (
            <tr key={r.id}>
              <td>{r.id}</td>
              <td>{r.data}</td>
              <td>{r.hora}</td>
              <td>{r.cliente?.nome}</td>
              <td>{r.status}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
