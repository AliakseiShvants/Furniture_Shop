// export class Role {
//
//   private _id: number;
//   private _title: string;
//
//   constructor(title: string) {
//     this._title = title;
//   }
//
//   get id(): number {
//     return this._id;
//   }
//
//   set id(value: number) {
//     this._id = value;
//   }
//
//   get title(): string {
//     return this._title;
//   }
//
//   set title(value: string) {
//     this._title = value;
//   }
// }
export enum Role {
  USER,
  MANAGER,
  ADMIN
}
