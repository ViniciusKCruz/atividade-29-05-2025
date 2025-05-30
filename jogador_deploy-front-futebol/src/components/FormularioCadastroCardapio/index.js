// src\components\FormularioCadastro\index.js

import { useState } from "react";
import './styles.css'
import { useNavigate } from "react-router-dom";
import useMensagem from '../../hooks/useMensagem'
import MensagemFeedback from '../MensagemFeedback'
import logo from '../../assets/images/LogoRestauranteRatatullie.png'
import axios from 'axios'

function FormularioCadastro() {
    const [nome, setNome] = useState('')
    const [sexo, setSexo] = useState('')
    const [idade, setIdade] = useState('')
    const [altura, setAltura] = useState('')
    const [peso, setPeso] = useState('')
    const [posicao, setPosicao] = useState('')
    const [numCamisa, setNumCamisa] = useState('')
    const navigate = useNavigate()
    const { exibirMensagem , mensagem, tipoMensagem, visivel, fecharMensagem } = useMensagem()

    const cadastrarUsuario = async () => {
        try {
            const response = await axios.post('https://jogador-backend.onrender.com/usuarios', {nome, sexo, idade, altura, peso, posicao, numCamisa})
            exibirMensagem(response.data.mensagem || 'Usuário cadastrado com sucesso!', 'sucesso')
            setNome('')
            setSexo('')
            setIdade('')
            setAltura('')
            setPeso('')
            setPosicao('')
            setNumCamisa('')
        } catch (error) {
            let erroMsg = 'Erro ao conectar ao servidor.'
            if (error.response && error.response.data) {
                erroMsg = error.response.data.mensagem
                if (error.response.data.erros) {
                    erroMsg += ' ' + Object.values(error.response.data.erros).join(', ')
                }
            }
            exibirMensagem(erroMsg, 'erro')
        }
    }

    return (
        <div className="container">
            <img src={logo} alt="Logo da empresa" />
            <h2>Cadastro de Pratos</h2>
            <form onSubmit={(e) => {e.preventDefault(); cadastrarUsuario()}}>
                <input type="text" id="nome" placeholder="Nome do Prato" value={nome} onChange={(e) => setNome(e.target.value)} required />
                <textarea type="text" id="idade" placeholder="Descrição" value={idade} onChange={(e) => setIdade(e.target.value)} required />
                <input type="text" id="altura" placeholder="Preço" value={altura} onChange={(e) => setAltura(e.target.value)} required />
                <input type="text" id="peso" placeholder="Categoria" value={peso} onChange={(e) => setPeso(e.target.value)} required/>
                <input type="text" id="posicao" placeholder="Disponibilidade" value={posicao} onChange={(e) => setPosicao(e.target.value)} required />

                <input type="url" placeholder="URL da imagem" value={numCamisa} onChange={(e) => setNumCamisa(e.target.value)} required />
                <button type="submit">Cadastrar</button>
            </form>

            <MensagemFeedback
                mensagem={mensagem}
                tipo={tipoMensagem}
                visivel={visivel}
                onclose={fecharMensagem}
            />
        </div>
    )
}

export default FormularioCadastro
