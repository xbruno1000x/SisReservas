import React, { useEffect, useState } from 'react'
import axios from 'axios'

type Cliente = {
  id: number
  nome: string
  telefone: string
  email: string
}

export default function Clientes(){
  const [clientes, setClientes] = useState<Cliente[]>([])
  const [loading, setLoading] = useState(true)

  useEffect(()=>{
    axios.get('/api/clientes')
      .then(res=> setClientes(res.data))
      .catch(err=> console.error(err))
      .finally(()=> setLoading(false))
  },[])

  if(loading) return <div>Carregando clientes...</div>

  return (
    <div>
      <h2>Clientes</h2>
      <table style={{width: '100%', borderCollapse: 'collapse'}}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Telefone</th>
            <th>Email</th>
          </tr>
        </thead>
        <tbody>
          {clientes.map(c=> (
            <tr key={c.id}>
              <td>{c.id}</td>
              <td>{c.nome}</td>
              <td>{c.telefone}</td>
              <td>{c.email}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
