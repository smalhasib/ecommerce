import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getSingleUser } from "./userApi";
const initialState = {
  user: {},
  isLoading: false,
  isError: false,
};

export const fetchUser = createAsyncThunk(
  "user/fetchUser",
  async (id: string) => {
    const res = await getSingleUser(id);
    return res;
  }
);
export const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchUser.pending, (state, action) => {
        state.isError = false;
        state.isLoading = true;
      })
      .addCase(fetchUser.fulfilled, (state, action) => {
        state.isLoading = false;
        state.user = action.payload;
      })
      .addCase(fetchUser.rejected, (state, action) => {
        state.isError = true;
        state.isLoading = false;
        state.user = {};
      });
  },
});
export default userSlice.reducer;
