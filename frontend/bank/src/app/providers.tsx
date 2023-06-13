"use client";
import { Provider } from "react-redux";
import { store } from "../redux/store/store";
const Providers = ({ children }) => {
  return (
    <div>
      <Providers store={store}>{children}</Providers>
    </div>
  );
};

export default Providers;
