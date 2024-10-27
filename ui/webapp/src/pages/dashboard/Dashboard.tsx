import UserList from "../../components/UserList";
import useUsers from "../../hooks/useUsers";

const Dashboard = () =>{

const {users, error, isLoading} = useUsers();

return (<div>
{isLoading && <p>Loading...</p>}
{error && <p>{error}</p>}
<UserList users={users}/>
</div>
);
    };
export default Dashboard