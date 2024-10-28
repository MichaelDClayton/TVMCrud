import { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import { getUserById } from "../../services/user-service";
import { User } from "../../model/User";
import useUserById from "../../hooks/useUserById"
const UserDetails = () =>{
    const { id } = useParams<{id: string}>();
    const {user, errors, isLoading} = useUserById(id!);


    return (
        <div className="container mt-2">
            {isLoading && <p>Loading...</p>}
            {errors && <p className="text-danger">errors</p>}
                <div className="d-flex flex-row-reverse mb-2">
                    <button className="btn btn-sm btn-danger">Delete</button>
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
        </div>
        )
    }
export default UserDetails;