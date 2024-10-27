import apiClient from "../config/apiClient";
import { User } from "../model/User";
export const getUsers = () => {
    return apiClient.get<User>[]('http://localhost:8080/api/users');
    }