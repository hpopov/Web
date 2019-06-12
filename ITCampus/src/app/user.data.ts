export interface UserData {
  id: number;
  login: string;
  name: string;
  surname: string;
  authorities: string[];
}

export interface Credentials {
  username: string;
  password: string;
}

export interface AuthData {
  username: string;
  token: string;
}