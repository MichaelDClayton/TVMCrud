import { User } from "../model/User";

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

                <div className="card-body">
                {users.map(user =>
                    <div key={user.id}>
                    <div className="d-flex justify-content-between border-bottom-1 p-3 text-dark">
                        <div className="card-title m-0">
                        <h5>{user.firstName} {user.lastName}</h5>
                        <span className="fst-italic">{user.email}</span>
                       </div>
                    </div>
                    </div>
                )}
                </div>

            </div>

            );

    }
export default UserList