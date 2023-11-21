import Navbar from './Navbar';
import TodoList from './TodoList';

const App = () => {
    return(
        <div className="uk-container">
            <Navbar></Navbar>
            <TodoList></TodoList>
        </div>
    );
} 

//igualar a constante a uma arrow function serve p/ criar um escopo e não causar efeito colateral no componente

export default App;