import { Todo } from '../models/Todo';
import TodoListItem from './TodoListItem';

const TodoList = () => {
    const todos: Todo[] = [
        
    ];
    return(        
        <table className="uk-table">
            <caption>Lista de tarefas</caption>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Tarefa</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {   //as chaves são usadas p/ inserir código dentro das tags e o ? em todos serve p/ se caso não houver elementos em todos ele não mostrará um erro
                     todos?.map( //o map vai percorrer o todos e smp que tiver o map vc precisa colocar o key que deve ser uma chave única, nesse caso o id
                        todo => (<TodoListItem key={todo.id} todo={todo}></TodoListItem>)
                    )
                }
            </tbody>
        </table>
    );
}

export default TodoList;