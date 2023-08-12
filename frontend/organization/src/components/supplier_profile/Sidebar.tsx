import Cookies from "js-cookie";
import { useRouter } from "next/navigation";
import React from "react";

const Sidebar = ({ setShow }) => {
  const router = useRouter();
  const logOut = () => {
    Cookies.remove("org_accessToken");
    Cookies.remove("user_role");
    Cookies.remove("org_user_id");
    router.push("/");
  };
  return (
    <React.Fragment>
      <div className="h-screen fixed rounded-lg px-8 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
        <div className="space-y-5 ">
          <div
            onClick={() => {
              setShow("dashboard");
            }}
            className="flex items-center p-2 text-gray-900 rounded-lg cursor-pointer hover:bg-gray-100 dark:hover:bg-gray-700"
          >
            <svg
              aria-hidden="true"
              className="w-6 h-6 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path d="M2 10a8 8 0 018-8v8h8a8 8 0 11-16 0z"></path>
              <path d="M12 2.252A8.014 8.014 0 0117.748 8H12V2.252z"></path>
            </svg>
            <span className="ml-3">Dashboard</span>
          </div>
          <div
            onClick={() => {
              setShow("profile");
            }}
            className="flex items-center p-2 text-gray-900 rounded-lg cursor-pointer hover:bg-gray-100 dark:hover:bg-gray-700"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="w-6 h-6"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M17.982 18.725A7.488 7.488 0 0012 15.75a7.488 7.488 0 00-5.982 2.975m11.963 0a9 9 0 10-11.963 0m11.963 0A8.966 8.966 0 0112 21a8.966 8.966 0 01-5.982-2.275M15 9.75a3 3 0 11-6 0 3 3 0 016 0z"
              />
            </svg>

            <span className="flex-1 ml-3 whitespace-nowrap">Profile</span>
          </div>
          <div
            onClick={() => {
              setShow("addproduct");
            }}
            className="flex items-center p-2 text-gray-900 rounded-lg cursor-pointer hover:bg-gray-100 dark:hover:bg-gray-700"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              strokeWidth={1.5}
              stroke="currentColor"
              className="w-6 h-6"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M12 9v6m3-3H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>
            <span className="flex-1 ml-3 whitespace-nowrap">Add Products</span>
          </div>
          <div
            onClick={() => {
              setShow("products");
            }}
            className="flex items-center p-2 text-gray-900 rounded-lg cursor-pointer hover:bg-gray-100 dark:hover:bg-gray-700"
          >
            <svg
              aria-hidden="true"
              className="flex-shrink-0 w-6 h-6 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                fillRule="evenodd"
                d="M10 2a4 4 0 00-4 4v1H5a1 1 0 00-.994.89l-1 9A1 1 0 004 18h12a1 1 0 00.994-1.11l-1-9A1 1 0 0015 7h-1V6a4 4 0 00-4-4zm2 5V6a2 2 0 10-4 0v1h4zm-6 3a1 1 0 112 0 1 1 0 01-2 0zm7-1a1 1 0 100 2 1 1 0 000-2z"
                clipRule="evenodd"
              ></path>
            </svg>
            <span className="flex-1 ml-3 whitespace-nowrap">Products</span>
          </div>


          <div
            onClick={() => {
              setShow("pendingpayment");
            }}
            className="flex items-center p-2 text-gray-900 rounded-lg cursor-pointer hover:bg-gray-100 dark:hover:bg-gray-700"
          >
         <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
  <path strokeLinecap="round" strokeLinejoin="round" d="M12 6v12m-3-2.818l.879.659c1.171.879 3.07.879 4.242 0 1.172-.879 1.172-2.303 0-3.182C13.536 12.219 12.768 12 12 12c-.725 0-1.45-.22-2.003-.659-1.106-.879-1.106-2.303 0-3.182s2.9-.879 4.006 0l.415.33M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
</svg>

            <span className="flex-1 ml-3 whitespace-nowrap">Pending Payment</span>
          </div>

          <div
            onClick={logOut}
            className="flex items-center p-2 text-gray-900 rounded-lg cursor-pointer hover:bg-gray-100 dark:hover:bg-gray-700"
          >
            <svg
              aria-hidden="true"
              className="flex-shrink-0 w-6 h-6 text-gray-500 transition duration-75 dark:text-gray-400 group-hover:text-gray-900 dark:group-hover:text-white"
              fill="currentColor"
              viewBox="0 0 20 20"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                fillRule="evenodd"
                d="M3 3a1 1 0 00-1 1v12a1 1 0 102 0V4a1 1 0 00-1-1zm10.293 9.293a1 1 0 001.414 1.414l3-3a1 1 0 000-1.414l-3-3a1 1 0 10-1.414 1.414L14.586 9H7a1 1 0 100 2h7.586l-1.293 1.293z"
                clipRule="evenodd"
              ></path>
            </svg>
            <span className="flex-1 ml-3 whitespace-nowrap">logout</span>
          </div>
        </div>
      </div>
    </React.Fragment>
  );
};

export default Sidebar;
