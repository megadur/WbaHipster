import { IBngInfo } from 'app/shared/model/bng-info.model';

export interface ILineInfo {
  id?: number;
  lineId?: string;
  uplinkPort?: string;
  ipAddress?: string;
  tsLastQuery?: string;
  tsLastQuerySuccess?: string;
  bngInfo?: IBngInfo;
}

export class LineInfo implements ILineInfo {
  constructor(
    public id?: number,
    public lineId?: string,
    public uplinkPort?: string,
    public ipAddress?: string,
    public tsLastQuery?: string,
    public tsLastQuerySuccess?: string,
    public bngInfo?: IBngInfo
  ) {}
}
