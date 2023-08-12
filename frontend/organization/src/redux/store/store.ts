import { configureStore } from "@reduxjs/toolkit";
import cartReducer from "../freatures/cartSlice";

export const store = configureStore({
  reducer: {
    allCart: cartReducer,
  },
});
