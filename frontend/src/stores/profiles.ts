/* eslint-disable @typescript-eslint/no-explicit-any */
import { create } from 'zustand';

export type ProfileState = {
    isProfile: boolean;
    isPassword: boolean;
    isRequests: boolean;
    isResults: boolean;
    setIsState: (stateName: keyof ProfileState, option: boolean) => void;
};

export const useProfileSlice = create<ProfileState>()((set: any) => ({
    isProfile: true,
    isPassword: false,
    isRequests: false,
    isResults: false,
    setIsState: (stateName, option) => set(() => ({ [stateName]: option })),
}));
