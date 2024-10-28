const NewUser = () => {
    return(
           <div className="d-flex justify-content-center align-items-center mt-2">
                <div className="container col-sm-4 col-sm-8 col-xs-12">
                    <form>
                        <div className="mb-3">
                            <label htmlFor="firstName" className="form-label">
                                First Name
                            </label>
                            <input type="text" id="firstName" className="form-control"/>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="lastName" className="form-label">
                                Last Name
                            </label>
                            <input type="text" id="lastName" className="form-control"/>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">
                                Email
                            </label>
                            <input type="text" id="email" className="form-control"/>
                        </div>
                    </form>
                </div>
            </div>
        );
    };
export default NewUser;