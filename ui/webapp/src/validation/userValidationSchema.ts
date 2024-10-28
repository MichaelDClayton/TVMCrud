import * as Yup from 'yup';
const userValidationSchema = Yup.object({
    firstName: Yup.string().required('First name is required')
    .min(3,'First name must be at least 3 characters long'),
    lastName: Yup.string().required('Last name is required')
     .min(3,'Last name must be at least 3 characters long'),
    email: Yup.string().required('Email is required')
    })
export default userValidationSchema