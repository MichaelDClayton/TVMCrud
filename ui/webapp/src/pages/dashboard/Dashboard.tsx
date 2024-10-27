import UserList from "../../components/UserList";
import { User} from "../../model/User";
import apiClient from "../../config/apiClient";
import { useEffect, useState } from "react";
const Dashboard = () =>{
const [users, setUsers] = useState<User[]>([]);
const [error, setErrors] = useState(null);
const [isLoading, setLoader] = useState(false);
useEffect(() => {
    //call backend api.
    apiClient
    .get("http://localhost:8080/api/users")
    .then((response) => {
        setUsers(response.data);
        setLoader(false);
        })
    .catch((error) => setErrors(error))
    .finally(() => setLoader(false));
    }, []);




return <div>
<UserList users={users}/>
</div>
    };
export default Dashboard