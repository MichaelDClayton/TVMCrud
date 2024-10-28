import { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { getUserById } from "../../services/user-service";
import { User } from "../../model/User";
import useUserById from "../../hooks/useUserById"
import ConfirmDialog from "../../components/ConfirmDialog";

const UserDetails = () =>{

    const { id } = useParams<{id: string}>();
    const {user, errors, isLoading} = useUserById(id!);
    const [showDialog, setShowDialog] = useState<boolean>(false);
    if(!id){
            return <p className="text-danger">Invalid User ID</p>
            }
    const handleConfirm = () => {
        console.log("Confirm is clicked");
        setShowDialog(false);
        }
    const handleCancel = () => {
            console.log("Cancel is clicked");
            setShowDialog(false);
            }


    return (
        <div className="container mt-2">
            {isLoading && <p>Loading...</p>}
            {errors && <p className="text-danger">errors</p>}
                <div className="d-flex flex-row-reverse mb-2">
                    <button className="btn btn-sm btn-danger" onClick={() => setShowDialog(true)}>Delete</button>
                    <button className="btn btn-sm btn-warning mx-2">Edit</button>
                    <button className="btn btn-sm btn-secondary">Back</button>
                </div>
                    <div className="card">
                        <div className="card-body p-3">
                            <table className="table table-borderless table-responsive">
                                <tbody>

                                    <tr>
                                        <th>Name</th>
                                        <td>{user?.firstName} {user?.lastName}</td>
                                    </tr>
                                     <tr>
                                        <th>Email</th>
                                        <td>{user?.email}</td>
                                    </tr>
                                </tbody>

                            </table>
                        </div>
                    </div>
                    <ConfirmDialog title="Confirm Delete"
                    message="Are you sure you want to delete this User?"
                    show={showDialog}
                    onCancel={handleCancel}
                    onConfirm={handleConfirm}
                    />
        </div>
        )
    }
export default UserDetails;