import { createContext } from 'react';
import { TodoContextType } from "./TodoContextType";

export const TodoContext = createContext<TodoContextType>({
    todos: [
        { id: 1, title: 'Ir ao supermercado', done: true },
        { id: 2, title: 'Ir a academia', done: false }
    ],
    addTodo: () => {},
    removeTodo: () => {},
    toggle: () => {}
});