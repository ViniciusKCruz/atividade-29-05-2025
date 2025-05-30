// src\pages\Lista\index.js

import PaginaInicial from '../../components/PaginaInicial'
import { useNavigate } from 'react-router-dom'


function PaginaInicio() {
    const navigate = useNavigate()
    
return (
        <div className='pagina-lista-usuarios'>
            <div className='container'>
                <PaginaInicial/>
            </div>
        </div>
    )
}

export default PaginaInicio