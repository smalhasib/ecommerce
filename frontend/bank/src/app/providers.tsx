"use client";
import { Provider } from "react-redux";
import { store } from "../redux/store/store";
const Providers = ({ children }: { children: React.ReactNode }) => {
  return (
    <div>
      <Provider store={store}>{children}</Provider>
    </div>
  );
};

export default Providers;
