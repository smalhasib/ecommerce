"use client";
import { Provider } from "react-redux";
import { store } from "@/redux/store/store";
import { QueryClientProvider, QueryClient } from "react-query";
const Providers = ({ children }: { children: React.ReactNode }) => {
  const queryClient = new QueryClient();
  return (
    <>
      <QueryClientProvider client={queryClient}>
        <div>
          <Provider store={store}>{children}</Provider>
        </div>
      </QueryClientProvider>
    </>
  );
};

export default Providers;
