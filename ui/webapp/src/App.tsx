import Dashboard from './pages/dashboard/Dashboard';
import React from 'react';
import "./index.css";
import Navbar from "./components/Navbar";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import NewUser from "./pages/user/NewUser";
import UserDetails from "./pages/user/UserDetails";

const App = () => {
    return (
      <BrowserRouter>
          <Navbar />
          <Routes>
            <Route path="/" element={<Dashboard/>}/>
            <Route path="/new" element={<NewUser/>}/>
            <Route path="/view/:id" element={<UserDetails/>}/>
            <Route path="/edit/:id" element={<NewUser/>}/>
          </Routes>

      </BrowserRouter>
        )
    }
export default App