import { ILineInfo } from 'app/shared/model/line-info.model';

export interface IBngInfo {
  id?: number;
  endSz?: string;
  lineId?: string;
  portName?: string;
  tsLastQuery?: string;
  tsLastQuerySuccess?: string;
  lineInfos?: ILineInfo[];
}

export class BngInfo implements IBngInfo {
  constructor(
    public id?: number,
    public endSz?: string,
    public lineId?: string,
    public portName?: string,
    public tsLastQuery?: string,
    public tsLastQuerySuccess?: string,
    public lineInfos?: ILineInfo[]
  ) {}
}
