import { useState } from 'react';
import Login from './Components/Login';
import Signup from './Components/Signup';
import ProtectedPage from './Components/ProtectedPage';

function App(){
    const[page, setPage] = useState('login');

    return (
        <div>
            <nav>
                <button onClick={()=> setPage('login')}>Login</button>
                <button onClick={()=> setPage('signup')}>Signup</button>
                <button onClick={()=> setPage('protected')}>protected</button>
            </nav>

            {page === 'login' && <Login />}
            {page === 'signup' && <Signup />}
            {page === 'protected' && <ProtectedPage />}
        </div>
    );
}

export default App;