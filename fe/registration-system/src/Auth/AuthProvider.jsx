/* eslint-disable react/prop-types */
import { createContext, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";

export const AuthContext = createContext();

export const useAuth = () => {
  return useContext(AuthContext);
};

export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();

  const getSession = () => {
    const localStorageUser = localStorage.getItem("session");
    if (!localStorageUser) {
      return null;
    }
    return JSON.parse(localStorageUser);
  };
  /**
   * setToken from localstorage
   */
  const setSessionInLocalStorage = (user) => {
    localStorage.setItem("session", JSON.stringify(user));
    localStorage.setItem("token", String(user.token));

    return true;
  };
  const auth = getSession();

  const [session, setSession] = useState(auth);
  const setAuth = (user) => {
    setSessionInLocalStorage(user);
    setSession(getSession());
  };

  function logout() {
    localStorage.removeItem("session");
    navigate("/");
  }

  const user = session;
  return <AuthContext.Provider value={{ user, setAuth, logout }}>{children}</AuthContext.Provider>;
};
