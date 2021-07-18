export class Endpoint {
  id?: number;
  name: string;
  userName?: string;

  constructor(id?: number, name?: string, userName?: string) {
    this.id = id;
    this.name = name;
    this.userName = userName;
  }
}
