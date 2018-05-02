import {Status} from './status';

export class Order {
  id: number;
  manager: string;
  status: Status;
  creatingDate: string;
  completionDate: string;
}
