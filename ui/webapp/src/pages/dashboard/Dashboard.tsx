import UserList from "../../components/UserList";
import useUsers from "../../hooks/useUsers";

const Dashboard = () =>{
console.log("testing");
const {users, error, isLoading} = useUsers();

return (<div className="container">
{isLoading && <p>Loading...</p>}
{error && <p>{error}</p>}
<UserList users={users}/>
</div>
);
    };
export default Dashboard