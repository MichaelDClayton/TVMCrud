import {useEffect, useState} from 'react';
import {User} from "../model/User";
import {getUserById} from "../services/user-service";
const useUserById = (id: string) => {
    const [user, setUser] = useState<User | undefined>();
    const [errors, setErrors] = useState<string>("");
    const [isLoading, setLoader] = useState<boolean>(false);

    useEffect(() =>{
       if(id){
           setLoader(true);
            getUserById(id)
           .then((response) => setUser(response.data))
           .catch((error) => setErrors(error.message))
           .finally(() => setLoader(false));
           }
        }, []);
        return {user, errors, isLoading, setLoader, setErrors}
    }
export default useUserById