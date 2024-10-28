import {useFormik} from "formik";
import userValidationSchema from "../../validation/userValidationSchema";
import {saveOrUpdateUser} from "../../services/user-service";
import {useState} from 'react';
import {useNavigate} from "react-router-dom";

const NewUser = () => {
    const navigate = useNavigate();
    const [error, setErrors] = useState<string>("")
    const formik = useFormik({
        initialValues:{
            firstName: "",
            lastName: "",
            email: ""
            },
        onSubmit:(values: User) =>{
            saveOrUpdateUser(values)
            .then((response) => {
                if(response && response.status == 201){
                    navigate("/");
                    }
                })
            .catch((error) => {
            setErrors(error.message);
            });
        },
        validationSchema:userValidationSchema
        });
    return(
           <div className="d-flex justify-content-center align-items-center mt-2">
           {error && <p>{error}</p>}
                <div className="container col-sm-4 col-sm-8 col-xs-12">
                    <form onSubmit={formik.handleSubmit}>
                        <div className="mb-3">
                            <label htmlFor="firstName" className="form-label">
                                First Name
                            </label>
                            <input type="text" id="firstName" className="form-control"
                            value={formik.values.firstName}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            />
                            {formik.touched.firstName && formik.errors.firstName ? <div className="text-danger fst-italic">
                               {formik.errors.firstName}
                                </div> : null
                                }
                        </div>
                        <div className="mb-3">
                            <label htmlFor="lastName" className="form-label">
                                Last Name
                            </label>
                            <input type="text" id="lastName" className="form-control"
                            value={formik.values.lastName}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            />
                            {formik.touched.lastName && formik.errors.lastName ? <div className="text-danger fst-italic">
                                                           {formik.errors.lastName}
                                                            </div> : null
                                                            }
                        </div>
                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">
                                Email
                            </label>
                            <input type="email" id="email" className="form-control"
                            value={formik.values.email}
                            onChange={formik.handleChange}
                            />
                            {formik.touched.email && formik.errors.email ? <div className="text-danger fst-italic">
                                                                                       {formik.errors.email}
                                                                                        </div> : null
                                                                                        }
                        </div>
                        <button className="btn btn-sm btn-primary btn-outline-light" type="submit">Save</button>
                    </form>
                </div>
            </div>
        );
    };
export default NewUser;