import { User } from "../model/User";
import { Link } from "react-router-dom";
interface Props {
    users: User[];
    }
const UserList = ({users}: Props) => {
            /* return (
                <div>
            <table border={1}>
                <thead>
                <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                </tr>
                </thead>
                <tbody>
                {users.map(user =>
                    <tr key={user.id}>
                    <td>{user.firstName}</td>
                    <td>{user.lastName}</td>
                    <td>{user.email}</td>
                    </tr>)}
                </tbody>
            </table>
            </div>); */

           return(


            <div className="card">
                <h5 className="card-header">
                User
                 </h5>


                {users.map(user =>
                    <div class="card">
                      <div class="card-body">
                    <Link key={user.id} to={`/view/${user.id}`} style={{textDecoration: "none"}}>
                        <div className="d-flex justify-content-between border-bottom-1 p-3 text-dark">
                            <div className="card-title m-0">
                            <h5>{user.firstName} {user.lastName}</h5>
                            <span className="fst-italic">{user.email}</span>
                           </div>
                        </div>
                    </Link>
                    </div>
                    </div>
                )}


            </div>

            );

    }
export default UserList