import UserList from "../../components/UserList";
import { User} from "../../model/User";
import apiClient from "../../config/apiClient";
import { useEffect } from "react";
const Dashboard = () =>{
useEffect(() => {
    //call backend api.
    apiClient.get("http://localhost:8080/api/users")
    .then((response) => console.log(response))
    .catch((error) => console.log(error));
    }, []);


     const users: User[] = [
                    {id:1,
                        firstName: "Mike",
                        lastName: "Clayton",
                        email:"mc@aol.com"},
                    {id:2,
                        firstName: "John",
                        lastName: "Smith",
                        email:"js@fish.net"}
                    ];


return <div>
<UserList users={users}/>
</div>
    };
export default Dashboard