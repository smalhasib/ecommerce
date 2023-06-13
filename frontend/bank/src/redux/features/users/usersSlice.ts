import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

const initialState = {
  jobs: [],
  isLoading: false,
  isError: false,
  isSuccess: false,
  error: "",
};

const usersSlice = createSlice({
    name: "users",
    initialState,
    reducers:{},
    extraReducers: (builder)=>{
      
    }
})
