import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { GetAllusers } from "./usersApi";

type errorType = {error : string | undefined}
const initialState = {
  users: [],
  isLoading: false,
  isError: false,
  error: "",
};

export const fetchUsers = createAsyncThunk("users/fetchusers", async () => {
  const users = await GetAllusers();
  return users;
});

const usersSlice = createSlice({
  name: "users",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchUsers.pending, (state, action) => {
        state.isLoading = true;
        state.isError = false;
      })
      .addCase(fetchUsers.fulfilled, (state, action) => {
        state.isError = false;
        state.isLoading = false;
        state.users = action.payload;
      })
      .addCase(fetchUsers.rejected, (state, action) => {
        state.isError = true;
        state.isLoading = false;
        // state.error = action.error?.message;
      });
  },
});
export default usersSlice.reducer;