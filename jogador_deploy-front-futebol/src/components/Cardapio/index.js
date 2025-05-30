// src\components\ListaDeUsuarios\index.js

import { useState, useEffect } from "react";
import axios from "axios";
import './styles.css'
import { useNavigate } from 'react-router-dom'

function Cardapio() {
    const [pratos, setPratos] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        const carregarPratos = async () => {
            try {
                const response = await axios.get('LINK DA API')
                setPratos(response.data)
            } catch (error) {
                alert('Erro ao buscar Pratos: ', error)
                setPratos([])
            }
        }
        carregarUsuarios()
    }, [])

    return (
        <ul id="listaUsurios" className="lista-usuarios">
            {usuarios.length === 0 ? (
                <li>Nenhum prato cadastrado</li>
            ) : (
                usuarios.map( usuario => (
                    <div>
                    <li key={usuario.id}>
                        <strong>Nome do prato: </strong> {usuario.nome}<br />
                        <strong>Descrição: </strong> {usuario.sexo}<br />
                        <strong>Preço: </strong> {usuario.idade}<br />          
                        <strong>Categoria: </strong> {usuario.altura}<br />
                        <strong>Disponibilidade </strong> {usuario.peso}<br />
                        <strong><img url=""/></strong> {usuario.posicao}<br/>           
                    </li>

                    <button onClick={() => navigate('/Cadastro')} className='link-voltar'>
                    Cadastrar Pratos
                    </button>
                    </div>                  
                ))
            )}
        </ul>
    )
    
}

export default Cardapio
