import { User } from "../model/User";

interface Props {
    users: User[];
    }
const UserList = ({users}: Props) => {
            return (
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
            </div>);

    }
export default UserList