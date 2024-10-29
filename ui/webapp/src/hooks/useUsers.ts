import { getUsers } from "../services/user-service";
import { useEffect, useState } from 'react';
import { User } from "../model/User";

const useUsers = () => {

const [users, setUsers] = useState<User[]>([]);
const [error, setErrors] = useState(null);
const [isLoading, setLoader] = useState(false);
useEffect(() => {
    setLoader(true);
    //call backend api.
    //apiClient
    //.get("http://localhost:8080/api/users")
     getUsers()
    .then((response) => {
        setUsers(response.data);
        setLoader(false);
        })
    .catch((error) => setErrors(error))
    .finally(() => setLoader(false));
    }, []);
    return {users, error, isLoading}
    }
export default useUsers;