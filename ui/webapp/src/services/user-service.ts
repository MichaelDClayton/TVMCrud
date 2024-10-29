import apiClient from "../config/apiClient";
import { User } from "../model/User";
export const getUsers = () => {
    return apiClient.get<User[]>('http://localhost:8080/api/users');
    }
export const getUserById = (id: string) => {
        return apiClient.get<User>(`http://localhost:8080/api/user/${id}`);
    }

export const deleteUserById = (id: string) =>{
        return apiClient.delete<void>(`http://localhost:8080/api/user/${id}`);
    }

export const saveOrUpdateUser = (user: User) => {
    if(user.id != undefined || user.id != null){
            return apiClient.put<User>(`http://localhost:8080/api/user/${user.id}`, user);
        }
    return apiClient.post<User>(`http://localhost:8080/api/user`, user);
    }
