export class Order {
  private _id: number;
  private _manager: string;
  private _status: string;
  private _creatingDate: string;
  private _completionDate: string;


  constructor(id: number, manager: string, status: string, creatingDate: string, completionDate?: string) {
    this._id = id;
    this._manager = manager;
    this._status = status;
    this._creatingDate = creatingDate;
    this._completionDate = completionDate;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get manager(): string {
    return this._manager;
  }

  set manager(value: string) {
    this._manager = value;
  }

  get status(): string {
    return this._status;
  }

  set status(value: string) {
    this._status = value;
  }

  get creatingDate(): string {
    return this._creatingDate;
  }

  set creatingDate(value: string) {
    this._creatingDate = value;
  }

  get completionDate(): string {
    return this._completionDate;
  }

  set completionDate(value: string) {
    this._completionDate = value;
  }
}
