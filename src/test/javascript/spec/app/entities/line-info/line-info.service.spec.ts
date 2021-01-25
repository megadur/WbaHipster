import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LineInfoService } from 'app/entities/line-info/line-info.service';
import { ILineInfo, LineInfo } from 'app/shared/model/line-info.model';

describe('Service Tests', () => {
  describe('LineInfo Service', () => {
    let injector: TestBed;
    let service: LineInfoService;
    let httpMock: HttpTestingController;
    let elemDefault: ILineInfo;
    let expectedResult: ILineInfo | ILineInfo[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LineInfoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new LineInfo(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a LineInfo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new LineInfo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a LineInfo', () => {
        const returnedFromService = Object.assign(
          {
            lineId: 'BBBBBB',
            uplinkPort: 'BBBBBB',
            ipAddress: 'BBBBBB',
            tsLastQuery: 'BBBBBB',
            tsLastQuerySuccess: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of LineInfo', () => {
        const returnedFromService = Object.assign(
          {
            lineId: 'BBBBBB',
            uplinkPort: 'BBBBBB',
            ipAddress: 'BBBBBB',
            tsLastQuery: 'BBBBBB',
            tsLastQuerySuccess: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a LineInfo', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
